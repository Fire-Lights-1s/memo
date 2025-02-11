# LVM 
커널에 구현된 기능으로 여러 개의 HDD 용량을 합쳐서 하나의 파일 시스템으로 구성하고 스토리지 확장 및 변경에 있어 유연한 대처를 할 수 있다

## 구성
![LVM 구성](<../images/LVM_구성.png>)


파티션 상태 확인
```shell
df -Th
fdisk -l /dev/sda
```

disk의 남은 용량 파티션(예시)
```shell
[root@wbs-dbsafer ~]# fdisk /dev/sda

Welcome to fdisk (util-linux 2.32.1).
Changes will remain in memory only, until you decide to write them.
Be careful before using the write command.


Command (m for help): n
Partition type
   p   primary (2 primary, 0 extended, 2 free)
   e   extended (container for logical partitions)
Select (default p): p
Partition number (3,4, default 3):
First sector (40286208-67108863, default 40286208):
Last sector, +sectors or +size{K,M,G,T,P} (40286208-67108863, default 67108863):

Created a new partition 3 of type 'Linux' and of size 12.8 GiB.

Command (m for help): t
Partition number (1-3, default 3): 3
Hex code (type L to list all codes): 8e

Changed type of partition 'Linux' to 'Linux LVM'.

Command (m for help): p

Disk /dev/sda: 32 GiB, 34359738368 bytes, 67108864 sectors
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: dos
Disk identifier: 0x0c5717dc

Device     Boot    Start      End  Sectors  Size Id Type
/dev/sda1  *        2048  2099199  2097152    1G 83 Linux
/dev/sda2        2099200 40286207 38187008 18.2G 8e Linux LVM
/dev/sda3       40286208 67108863 26822656 12.8G 8e Linux LVM

Command (m for help): w
The partition table has been altered.
Syncing disks.
```


### Physical volume (PV) 생성
```shell
pvcreate /dev/sda3

# pv 확인
pvs
```

###  Volume group (VG) 생성
```shell
vgcreate homevg /dev/sda3

# vg 확인
vgs
```

### Logical volume (LV) 생성
lvcreate 명령어에 “-n” 옵션으로 생성할 LV 이름을 지정, “-L” 옵션으로 LV에 할당할 용량을 지정하고 마지막으로 LV를 생성할 VG를 지정하여 LV를 생성

“-L 사이즈 지정” 방식 외에 “-l +100%FREE”를 사용하면 VG의 남은 용량을 모두 할당
```shell
lvcreate -n homelv -L 12.79G homevg
lvcreate -n homelv -l +100%FREE homevg

# lv 조회
lvs 
```


### 파일 시스템 생성 및 마운트
파일 시스템 생성
```shell
[root@wbs-dbsafer ~]# mkfs.xfs /dev/mapper/homevg-homelv
meta-data=/dev/mapper/homevg-homelv isize=512    agcount=4, agsize=838144 blks
         =                       sectsz=512   attr=2, projid32bit=1
         =                       crc=1        finobt=1, sparse=1, rmapbt=0
         =                       reflink=1    bigtime=0 inobtcount=0
data     =                       bsize=4096   blocks=3352576, imaxpct=25
         =                       sunit=0      swidth=0 blks
naming   =version 2              bsize=4096   ascii-ci=0, ftype=1
log      =internal log           bsize=4096   blocks=2560, version=2
         =                       sectsz=512   sunit=0 blks, lazy-count=1
realtime =none                   extsz=4096   blocks=0, rtextents=0
Discarding blocks...Done.
```

`/home` 에 생성한 파일 시스템 마운트
```shell
[root@wbs-dbsafer ~]# mount /dev/mapper/homevg-homelv /home
[root@wbs-dbsafer ~]# df -Th
Filesystem                Type      Size  Used Avail Use% Mounted on
devtmpfs                  devtmpfs  3.8G     0  3.8G   0% /dev
tmpfs                     tmpfs     3.8G     0  3.8G   0% /dev/shm
tmpfs                     tmpfs     3.8G  8.5M  3.8G   1% /run
tmpfs                     tmpfs     3.8G     0  3.8G   0% /sys/fs/cgroup
/dev/mapper/rl-root       xfs        15G  2.4G   13G  16% /
/dev/sda1                 xfs      1014M  199M  816M  20% /boot
tmpfs                     tmpfs     770M     0  770M   0% /run/user/0
/dev/mapper/homevg-homelv xfs        13G  124M   13G   1% /home
```

##### 자동 마운트
`/etc/fstab` 마지막 줄에 내용 추가

```shell
# 디바이스 마운트포인트 파일시스템타입 옵션 덤프 파일시스템체크
/dev/mapper/homevg-homelv       /home   xfs     defaults        0 0

```


# 마운트/언마운트 시 사라지는 데이터 
