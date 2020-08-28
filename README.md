# JsonFlattenerApp
Program to flatten any given valid json, with exception of json containing arrays

## Prerequisites:
 Java 9 or greater <br/>
 Maven
 
## Build:
You can build this project by running the below command in the root directory of the project. The output should be a jar file in the target directory 
> mvn clean install

This will output an executable jar in the target directory with name 
>json_flat-0.1.jar

## How to run:
The process to flatten a json string can be run using the below syntax
>echo '\<VALID_JSON_INPUT\>' | java -jar '\<PATH_TO_JAR\>'

For example: <br/>
```
$ echo '{"a" : 1, "b" : {"c" : true, "d" : 1.0, "e" :{ "f" : false, "g" : -2}}}' | java -jar json_flat-0.1.jar
```

The process is capable of handling multiline inputs as well. For example:
```
$ echo '{
"a" : 1,
"b" : {
"c" : true,
"d" : 2.0
}
}'|java -jar json_flat-0.1.jar
```