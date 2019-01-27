package html4tree

import java.io.File

fun main(args: Array<String>){
    if (args.isEmpty())
        return help()
   
   val top_dir = File(args[0])

   top_dir.walkTopDown().filter({it -> it.isDirectory()}).forEach {
      process_dir(it);
   }
}

fun process_dir(curr_dir: File){
    val exclude: List<String> = listOf(curr_dir.getName(), "index.html").sorted()

    val css = """
              <style>
              ul {
                list-style-type: none;
              }
              </style>
              """

    val index_top = """<!doctype html>
<html>
     <head>
        <title>${curr_dir.getName()}</title>
        ${css}
     </head>
     <body>
       <h1>${curr_dir.getName()}</h1>
       <ul>
          <li><a style="display:block; width:100%" href="./..">&#x21B0; ..</a></li>
""" 

    val index_middle = fun():String{ 
        var l=""

        curr_dir.walkTopDown().maxDepth(1).sorted().forEach {
           if(it.getName() !in exclude) {
              l += """          <li><a style="display:block; width:100%" href="./${it.getName()}">${if (it.isDirectory()) { "&#128193;" } else { "&rtrif;" }} ${it.getName()}</a></li>"""+"\n"
           }
        }

        return l;
     } 

   val index_bottom="""
       </ul>
    </body>
</html>
"""

   File(curr_dir,"index.html").writeText(index_top+index_middle()+index_bottom)

}

fun help() {
    println("ERROR: help has not been written yet!")
}
