package html4tree

import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty())
        return help()

    val top_dir = File(args[0])
    require(top_dir.exists() && top_dir.isDirectory())

    val ll = LinkedList()

    ll.push(top_dir)

    var d: File? = ll.pull()

    while(d != null && d.isDirectory()){
        process_dir(d)
        d.listFiles().forEach {
            if(it.isDirectory()){
                ll.push(it)
            }
        }
        d = ll.pull()
    }
}

fun process_ignore_file(curr_dir: File): List<String> {

    val ignore_filename = ".html4ignore"
 
    val ignore_file_path = curr_dir.getAbsolutePath()+"/"+ignore_filename

    val ignore_file = File(ignore_file_path)

    val files_to_exclude = mutableListOf<String>()

    if(ignore_file.exists()){
       val ignored_strings = mutableListOf<String>()

       ignore_file.forEachLine { ignored_strings.add(it) }

       curr_dir.list().sorted().forEach {
           val current = it
           ignored_strings.forEach { i_string ->
              if(("^"+i_string+"$").toRegex().matches(current)){
                 files_to_exclude.add(current)
              }
         }
       }
    }

    if ("index.html" !in files_to_exclude)
       files_to_exclude.add("index.html")


    return files_to_exclude
}
 
fun process_dir(curr_dir: File){
    
    val exclude: List<String> = process_ignore_file(curr_dir)

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
           if((it.getName() !in exclude) && (it != curr_dir)) {
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
