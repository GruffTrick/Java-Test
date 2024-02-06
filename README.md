# Java-Test
A message validator that validates whether an array of byte code elements is in the correct format.  
Finds Start Text (STX) byte and End Text (ETX) byte then reads the data, using Data Link Escapes (DLEs) for reading in STX and ETX bytes as data.
Then compares the value of data within the STX and ETX bytes to an LRC checksum value.


## To Do List:
TODO: Implementation Here
1. ~~Check for Start-of-Text (STX) byte.~~
2. ~~Check for End-of-Text (ETX) byte.~~
3. ~~Find Data Link Escape (DLE) and distinguish next character from STX,ETX,DLE~~
4. ~~Handle real Data within the array: The Longitudinal Redundancy Check (LRC)~~
    - ~~The Exclusive-OR of all the bytes of the message, excluding STX/DLE but including ETX.~~
5. ~~Write Unit Tests to validate different use cases.~~

