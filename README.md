# gscanner-java
Allows you to scan a string into a list of tokens based on a given set of splitting tokens.
 
 
gscanner-java is a simple Java library that helps you quickly scan a string and split it into 
substrings based on an array of supplied tokens.
 
This library benchmarks very fast and is stable.

There are times when you either can do without the overhead of regular expressions, or the tokens required to 
split a string are a finite number.

There is no need to resort to regular expressions in this case.

This simple library lends itself as an hi-speed scanner/splitter and returns an array containing the substrings of the original
string. Whether you would love to retain the splitting tokens in the scanner's output is totally up to you! 

Simply set the ```includeTokensInOutput``` property of your ```GScanner``` to true to retain the splitting tokens.
Else set it to false.

The most sensible way of implementing this scan is to prioritize the splitter-tokens by sorting them by length in descending order.
So, longer tokens are first split out, and if they are not found, the consecutive shorter ones are.

If you have other requirements, modify the CustomScanner.java file and specify the priority order for scanning tokens by changing
the sort function as appropriate.



 ## Example

A beautiful example usage would be for scanning an arithmetic expression or other expression into a form wherein the input tokens are very visible:

```Java

    String code = "42sin(A)cos(B)+9sin(A)sinh(B)";

        CustomScanner cs = new CustomScanner(code, true, new String[]{"sin", "sinh", "cos", "+", "(", ")"});

        List<String> scan = cs.scan();
        System.err.println("scan: " + scan);

```
	



  The output would be:
  
  
  ```Java
scan: [42, sin, (, A, ), cos, (, B, ), +, 9, sin, (, A, ), sinh, (, B, )]
   ```



 

The square braces and the commas are just for formatting.

If you set 
```Java 
includeTokensInOutput
``` 
to false by doing this:

```Java

    String code = "42sin(A)cos(B)+9sin(A)sinh(B)";

        CustomScanner cs = new CustomScanner(code, false, new String[]{"sin", "sinh", "cos", "+", "(", ")"});

        List<String> scan = cs.scan();
        System.err.println("scan: " + scan);

```
  
  Then the output is:
  
```Java
scan: [42, A, B, 9, A, B]
```


Enjoy!

