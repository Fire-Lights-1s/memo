# nmap 
네트워크 스캐닝 툴
대상의 포트 및 서비스를 스캔 하는데 사용되는 툴이다

### TCP SYN Scan

```null
nmap -sS 192.168.0.1
```

SYN-ACK를 활용해 열린 포트를 체크합니다. 비교적 빠른 스캔이 가능합니다.

### TCP Connect Scan

```null
nmap -sT 192.168.0.1
```

TCP 연결 스캔은 SYN 스캔보다 정확하지만 더 느립니다. 마찬가지로 포트 스캔을 수행합니다.

### UDP Scan

```null
nmap -sU 192.168.0.1
```

UDP 포트 스캐닝을 수행합니다.

### Port Specification

```null
nmap -p 1-100 192.168.0.1
```

특정 포트 또는 포트 범위를 스캔합니다. 위의 경우 1부터 100까지의 포트를 스캔합니다.

### Aggressive Scan

```null
nmap -A 192.168.0.1
```

액티브 OS 감지, 버전 감지 및 스크립트 스캐닝과 같은 다양한 스캐닝 기술을 사용하여 보다 포괄적인 스캔을 수행합니다.

### OS Detection

```null
nmap -O 192.168.0.1
```

대상의 운영 체제를 감지할 수 있습니다.

### Version Detection

```null
nmap -sV 192.168.0.1
```

스캔된 포트의 서비스 및 버전 정보를 표시합니다.

### Verbose Output

```null
nmap -v 192.168.0.1
```

자세한 출력을 표시합니다. 캔 진행 상황을 실시간으로 확인할 수 있습니다.

### Input from List

```null
nmap -iL targets.txt
```

타겟 호스트 목록이 포함된 텍스트 파일을 사용하여 대량 스캐닝을 수행합니다.

### Timing Template

```null
nmap -T <0-5> 192.168.0.1
```

스캔 속도를 조정하는 데 사용됩니다. 0이 가장 느리고 5가 가장 빠릅니다.

## nmap 실습