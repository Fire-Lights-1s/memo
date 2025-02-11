# Redo log 란?
데이터의 변경 내역을 저장하는 파일 이다. 따라서 Buffer Cache에 올라 와있는 데이터 블록과 디스크 데이터 블록이 동기화가 필요 할 때 이 Redo log를 기록한  후 DBWR(DB writer)가 데이터를 변경시킨다

**Redo data가 Online Redo Log File에 기록되기 전까지 DBWR은 절대 데이터를 변경하지 않는다**

한 그룹의 Redo Log를 다 쓰고 다음 그룹으로 넘어가는 현상을 **Log Switch**라고 하고,
반복된 로그 스위치를 거치며 마지막 그룹에서 최초의 그룹으로 돌아와서(한바퀴 돌고) 
첫번 째 리두 데이터를 덮어쓰기 시작하는 것을 **Log Spinning**이라고 한다.
# Archive log 란?
REDO 로그가 스위치 할 때 아카이브 로그 모드로 운영한다면 아카이브 로그가 파일 형태로 만들어지고 저장된다.