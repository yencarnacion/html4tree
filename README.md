html4tree
=========

## Description

This program generates index.html files based on a file directory tree.
Think Apache mod_autoindex.
See [https://httpd.apache.org/docs/2.4/mod/mod_autoindex.html](https://httpd.apache.org/docs/2.4/mod/mod_autoindex.html).
It is written in Kotlin.

## License 

html4tree is copyrighted free software by Yamir Encarnación &lt;yencarnacion@webninjapr.com&gt;. You can redistribute it and/or modify it under the terms of the MIT license(see the file LICENSE).

## How to compile

To compile:

`$ ./gradlew`

## How to run

To obtain help:

```
java -jar ./build/libs/html4tree.jar -h
Usage: html4tree [OPTIONS] TOPDIR

Options:
  --max-level INT  Number of levels deep for which to generate an index.html
                   file
  -h, --help       Show this message and exit

Arguments:
  TOPDIR  Top directory to crawl
```  

To run:

`$ java -jar ./build/libs/html4tree.jar <top directory to index>`

To only generate the index.html file for the top directory:

`$ java -jar ./build/libs/html4tree.jar <top directory to index> --max-level 0`

## To exclude files from the generated index.html file

To exclude files place a `.html4ignore` file in the directory you wish to exclude particular files with each line of the file containing a valid regular expression that would match the filename you wish to exclude.

example:  to exclude files that end in `.txt` you could use the following in the `.html4ignore` file:

`.*.txt`

## Other

To delete all the index.html files generated with one command, do:

`$ find <top directory to crawl> -name index.html -delete`

