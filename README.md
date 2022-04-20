# 서울시 아파트 세권 비교 프로젝트 (JAVA programming) 

> 웹 크롤링을 활용하여 서울시 아파트 매매 실거래가와 세권 영향 요소들과의 관계 파악 프로젝트
>
>
## 프로젝트 개요
>
* 2022년 1월 서울특별시 아파트(매매) 실거래과와 세권 영향 요소들과의 관계를 파악
* java Selenium을 통해 각 아파트들의 위치와 세권 영향 요소들과의 최단 거리와 최소 시간을 크롤링
    * 세권 영향 요소 : `음식점` `병원` `은행` `편의점` `관공서` `마트` `카페` `주유소`.....
* 최종 결과값을 csv 파일로 file write하여 데이터 분석을 진행
    * 데이터 분석 : `회귀분석`



## 프로젝트 결과

스크린 샷과 결과값을 통해 프로젝트를 자세히 설명합니다.

![naver](https://user-images.githubusercontent.com/95575122/164195204-a742fd64-1668-4903-823f-48f93fa7f48c.png)
>
![result](https://user-images.githubusercontent.com/95575122/164195210-a843464f-52c6-4053-b1af-542a639afd39.png)
>
![csv](https://user-images.githubusercontent.com/95575122/164195197-c0eb2e23-68a2-4e00-ae15-f6b406f5fbf7.png)



## 프로젝트 결과 분석

* 전용면적이 1㎡넓어질수록 1614.926원, 층수가 1층 높아질수록 2104.165원의 집값이 오른다.
* 병원과의 거리가 1m 멀어질수록 58.3387원 집값이 떨어진다.
  (조정된 결정계수 : `0.569451`)
* 병원 외의 세권 영향 요소들은 P-Value 값이 0.1 이하이기에, 집값과의 관련성이 낮음을 확인하였다.


## 프로젝트 회고
* 현재는 1월 실거래가로 분석을 진행하였기에 다른 요소들과의 관련성이 적게 나온 것으로 예상된다. 
* 추후 데이터 범위를 넓혀 파이썬 AI 프로그래밍을 통해 미래 아파트 가격을 예측하는 프로젝트도 진행하고자 한다.


## 정보

김우현 – [https://woohyun.tistory.com/](https://woohyun.tistory.com/) – woohyun9509@gmail.com

MIT 라이센스를 준수하며 ``LICENSE``에서 자세한 정보를 확인할 수 있습니다.

[https://github.com/W00hyun-Kim/Project-SeoulApt.git](https://github.com/W00hyun-Kim/)
