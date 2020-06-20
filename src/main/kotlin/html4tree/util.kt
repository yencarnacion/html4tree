package html4tree

import java.io.File

class Entry (val data: File, var next: Entry?){

}

class LinkedList {
    var first: Entry? = null
    var last: Entry? = null

    fun push(f: File) {
        if(last == null){
            last = Entry(f, null)
            first = last
        } else {
            first?.next = Entry(f, null)
            first = first?.next
            first?.next = null
        }
    }

    fun pull(): File? {
        val l: Entry? = last
        if(l != null) {
            last = l.next
        }

        if(l == null){
            return null
        } else {
	    l.next = null
            return l.data
        }
    }

}