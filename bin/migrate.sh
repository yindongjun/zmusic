if [ -n "$1" ]; then
  newSqlFile=src/main/resources/db/migration/V`date +%Y%m%d%H%I%S`__${1}.sql
  touch "$newSqlFile"
  echo "迁移脚本生成成功: ""$newSqlFile"
else
  echo "请输入迁移脚本名称"
fi