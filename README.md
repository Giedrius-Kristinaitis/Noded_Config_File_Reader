# Noded_Config_File_Reader
Reads config files into a noded tree structure

# Example usage:

Config file content:
```
# this is a comment

general:
    some_value: 5
    some_other_section:
        some_other_value: 10
        some_string_value: this is sparta
        some_boolean_value: true
```

Java code snippet:
```java
ReaderInterface reader = new Reader(
    new IndentationCalculator(),
    new Validator(),
    new FileNameToInputStreamConverter(),
    new Trimmer(),
    new CommentRemover()
);

ValueFinderInterface valueFinder = new ValueFinder();

NodeInterface rootNode = reader.read("example.config");

int someValue = Integer.parseInt(valueFinder.findValue("general/some_value", rootNode));
int someOtherValue = Integer.parseInt(valueFinder.findValue("general/some_other_section/some_other_value", rootNode));
String someStringValue = valueFinder.findValue("general/some_other_section/some_string_value", rootNode);
boolean someBooleanValue = Boolean.parseBoolean(valueFinder.findValue("general/some_other_section/some_boolean_value", rootNode));

System.out.println("Some value: " + someValue);
System.out.println("Some other value: " + someOtherValue);
System.out.println("Some string value: " + someStringValue);
System.out.println("Some boolean value: " + someBooleanValue);
```

Output:
```
Some value: 5
Some other value: 10
Some string value: this is sparta
Some boolean value: true
```
