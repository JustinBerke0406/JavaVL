bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh              && sdk install java 8.0.292-open"
find . -name "*.java" -print | xargs javac -d bin
