html4tree
=========

## Description

This program generates index.html files based on a file directory tree.
Think Apache mod_autoindex.
See [https://httpd.apache.org/docs/2.4/mod/mod_autoindex.html](https://httpd.apache.org/docs/2.4/mod/mod_autoindex.html).
It is written in Kotlin.
It is very simple.

## Motivation

I wrote this program because I figured that it would take me less time to write it 
than to (continue) search(ing) for it using a search engine.

## License 

html4tree is copyrighted free software by Yamir Encarnación <yencarnacion@webninjapr.com>. You can redistribute it and/or modify it under the terms of the MIT license(see the file LICENSE).

## How to run

To compile:

`$ ./gradlew`

To run:

`$ java -jar ./build/libs/html4tree.jar <top directory to index>`

## Other

To delete all the index.html files generated with one command, do:

`$ find <top directory to crawl> -name index.html -delete`

