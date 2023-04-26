#include <stdio.h>
#include <stdlib.h>

main()   /* example1 */
{
   int pid, ppid, uid;

   pid = getpid();
   ppid = getppid();
   uid = getuid();

   printf("Process: %d\n", pid);
   printf("Parent: %d\n", ppid);
   printf("User: %d\n", uid);
   exit(0);
}
