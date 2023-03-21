@echo off

curl -OJL https://kumisystems.dl.sourceforge.net/project/gnuwin32/gzip/1.3.12-1/gzip-1.3.12-1-bin.zip
%SYSTEMROOT%\System32\WindowsPowerShell\v1.0\powershell.exe -command "Expand-Archive gzip-1.3.12-1-bin.zip"
del /F /Q gzip-1.3.12-1-bin.zip

curl -OJL https://datasets.imdbws.com/name.basics.tsv.gz
curl -OJL https://datasets.imdbws.com/title.akas.tsv.gz
curl -OJL https://datasets.imdbws.com/title.basics.tsv.gz
curl -OJL https://datasets.imdbws.com/title.crew.tsv.gz
curl -OJL https://datasets.imdbws.com/title.episode.tsv.gz
curl -OJL https://datasets.imdbws.com/title.principals.tsv.gz
curl -OJL https://datasets.imdbws.com/title.ratings.tsv.gz

mkdir dataset

.\gzip-1.3.12-1-bin\bin\gzip.exe -d name.basics.tsv.gz
move name.basics.tsv .\dataset

.\gzip-1.3.12-1-bin\bin\gzip.exe -d title.akas.tsv.gz 
move title.akas.tsv .\dataset

.\gzip-1.3.12-1-bin\bin\gzip.exe -d title.basics.tsv.gz
move title.basics.tsv .\dataset

.\gzip-1.3.12-1-bin\bin\gzip.exe -d title.crew.tsv.gz 
move title.crew.tsv .\dataset

.\gzip-1.3.12-1-bin\bin\gzip.exe -d title.episode.tsv.gz 
move title.episode.tsv .\dataset

.\gzip-1.3.12-1-bin\bin\gzip.exe -d title.principals.tsv.gz
move title.principals.tsv .\dataset

.\gzip-1.3.12-1-bin\bin\gzip.exe -d title.ratings.tsv.gz
move title.ratings.tsv .\dataset


rmdir /S /Q gzip-1.3.12-1-bin
echo Done!