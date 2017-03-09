# fileio
## I tested this on my Macbook pro and I got this result.
|Type   |  Time |
|:-----:|:-----:|
|Copy by byte| 2.862156 sec|
|Copy by 1 KB| 0.005684 sec|
|Copy by 4 KB| 0.002819 sec|
|Copy by 64KB| 0.001341 sec|
|Copy by BufferedReader | 0.057620 sec |

# Explanation

### Copy by byte
 - copy by byte is the slowest way to copy the file, the cursor ran by the character and wrote the new file by character as well so it might take very long time to finish this task.

### Copy by 1 kilobyte
 - copy by 1,024 byte per round is a little bit faster, comparing to one byte per round , it should be about 1000 times faster.

### Copy by 4 kilobyte
 - copy by 4,096 byte per round is faster than 1 kilobyte, of course.

### Copy by 64 kilobyte
 - copy by 64k byte per round, this must be the fastest way of the experiment, the another one is BufferReader, but BufferReader reads the file by a line, 64 kb must not in a line, I believe it should be more than that. so this is more than BufferReader.

### Copy by BufferedReader
 - copy by BufferedReader is copying by a line.
