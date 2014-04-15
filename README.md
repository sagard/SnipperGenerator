SnipperGenerator
================

This project generates a snippet from a review.

It takes as input the document(review) and words to search in review.Based on search terms it generates a 
relevant snippet.Search terms are highlighted by [[HIGHLIGHT]] ...[[ENDHIGHLIGHT]].

There are 3 main classes that are used here:

Main.java -- This is the main running class .We call the highlight_doc function passing it the pattern and document.
             It first calls the Search.java that searches for the pattern location in the document.
Then a minimum window is created which gives the minimum window which can contain all query in the document.
Final snippet is returned based on a snippet size .If the diff between max and min window is greater than 200(snippet size) we include 40 characters
that are before and after the string

Search.java -- It used Boyer Moore search algorithm to find all locations where the query terms are present in the document

FindWindow.java --  This function generates the minimum window that contains all the query words

Limitations:
This function would not match for similar words and looks for only partial or complete match of exact query terms.

