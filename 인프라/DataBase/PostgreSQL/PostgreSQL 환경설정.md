**postgresql.conf 수정**
```
...
#listen_addresses = 'localhost'         # what IP address(es) to listen on;
										# comma-separated list of addresses;
	                                    # defaults to 'localhost'; use '*' for all
	                                # (change requires restart)
#port = 5432                            # (change requires restart)
max_connections = 100                   # (change requires restart)
...

# 아래 내용으로 수정
listen_addresses = '*'
port = 5432

max_connection = 1024
```

**pg_hba.conf 수정**
```
...
local   replication     all                                     peer
host    replication     all             127.0.0.1/32            ident
host    replication     all             ::1/128                 ident

# 아래 내용으로 수정
local        all        all                           trust
host         all        all        0.0.0.0/0        trust        # IPv4
host         all        all        ::1/127          trust        #IPv6
```

수정 후 서비스 재구동