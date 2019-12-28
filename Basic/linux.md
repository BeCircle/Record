# Note of Linux

## SHELL

### Common Used Script

1. Auto remove old kernel

    ```bash
    dpkg -l 'linux-*' | sed '/^ii/!d;/'"$(uname -r | sed "s/\(.*\)-\([^0-9]\+\)/\1/")"'/d;s/^[^ ]* [^ ]* \([^ ]*\).*/\1/;/[0-9]/!d' | xargs sudo apt-get -y purge
    ```

1. Find a process and kill

    ```bash
    ps -ef | grep tomcat | grep -v grep | awk '{print $2}' | xargs kill -9 
    ```

1. Start a process backend and restore the log

    ```bash
    sudo ./Peanut >& ./peanut.log &
    ```

1. Clean Maven repo

   ```bash
   find /mnt/d/maven/rep  -name "*.lastUpdated" -exec grep -q "Could not transfer" {} \; -print -exec rm {} \;
   ```

## Linux Kernel

**Version:** kernel 1.0

