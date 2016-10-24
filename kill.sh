ps -ef|awk '$1 ~/root/ && /bootstrap/ && $0 !~/awk/ {system("kill 9 " $2)}'
ps -ef|awk '$1 ~/smas/ && /bootstrap/ && $0 !~/awk/ {system("kill 9 " $2)}'
