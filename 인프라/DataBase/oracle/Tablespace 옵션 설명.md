```sql
CREATE [BIGFILE | SMALLFILE(기본값)] 
TABLESPACE <테이블스페이스명> 
DATAFILE '<경로>' SIZE <크기> 
[EXTENT MANAGEMENT 
 [DICTIONARY | LOCAL(기본값) [AUTOALLOCATE(기본값) | UNIFORM SIZE <크기>]] 
] 
[SEGMENT SPACE MANAGEMENT [AUTO(기본값) | MANUAL]]
```

#### EXTENT MANAGEMENT
- Tablespace의 공간 할당은 Extent 단위로 진행

> DICTIONARY(구식)

사용 가능한 Extent에 대한 정보를 Data Dictionary에서 관리하는 방법입니다. 이방법은 각 Segment마다 다른 Extent 크기를 설정할 수 있지만, 쿼리 작업이나 DML작업을 할 때 만으로도 **내부적으로 많은 양의 Data Dictionary 조회 작업이 발생하는데 Extent 관리까지 하게 되면 Data Dictionary에 대한 경합 발생 가능성이 높아 현재는 사용되지 않는 방식**입니다.

> LOCAL

**Data File의 헤더에 비트맵을 통해 Extent의 사용 유무를 관리하는 방식**입니다. 각 Datafile에 비트맵을 사용하여 Resource의 사용량이 높아지지만 Data Dictionary Table 처럼 중요한 오브젝트의 경합을 줄이는 것이 더 중요하다. 기본값인 AUTOALLOCATE 방식을 사용하면 자동으로 Extent의 크기를 정하도록 위임 가능하고 UNIFORM 옵션을 사용하면 모든 Extent의 크기를 동일하게 설정 가능합니다.

#### SEGMENT SPACE MANAGEMENT

Segment의 공간 관리에 대한 옵션입니다. 쉽게 생각해서 **테이블의 공간 관리를 어떻게 할것인가...를 묻는 옵션**입니다. 현재는 AUTO 방식을 사용하고 있습니다.

> MANUAL(구식)

**Manual 방법은 Freelist를 사용해서 Insert가 가능한 블럭을 확인**할 수 있습니다. 테이블 생성시 블럭이 **일정 백분위 이하로 사이즈가 줄어들면 다시 사용가능한 블럭으로 Freelist에 등록하는 PCTUSED**라는 기준을 정해주는 옵션이 있습니다. 그런데 이 PCTUSED 설정으로 인해 공간이 있음에도 불구하고 **Extent를 계속 만들어야하는 상황**이 발생합니다.
각 블록에 나름 골고루 데이터가 분포되어 **모두 설정한 PCTUSED 이하로 안내려오는 경우**가 발생할 수 있습니다. 이런 경우 사용가능한 블럭이 없는것으로 Freelist에서 인식되어 **새로운 Extent를 할당**해야하는 상황이 발생할 수 있습니다. 이런 문제를 해결하기 위해 AUTO 방식을 사용합니다.

> AUTO

비트맵을 이용해서 비어있는 블록을 확인하는 방법입니다. PCTUSED 옵션이 사라지고 아래와 같이 비어있는 공간을 나타냅니다.

> fs1 0 ~ 25%  
> fs2 25 ~ 50%  
> fs3 50 ~ 75%  
> fs4 75 ~ 100%  
> full INSERT X  
> never used O

4개 등급으로 나누고 총 6가지 상태를 나타내는 Bitmap 블럭을 사용하여  segment를 관리합니다. 이러한 방법을 ASSM(Automatic Space Segment Management)라고 합니다.