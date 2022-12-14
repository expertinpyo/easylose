---
last modified on: 2022-10-06
---

![Ease logo](/assets/ease_logo_with_border.png)

# Ease
> Easy Lose

## ๐ฅ ์๋น์ค ์๊ฐ ์์
[![Ease๋ฅผ ์๊ฐํฉ๋๋ค](https://img.youtube.com/vi/xj-3bDGLxIg/0.jpg)](https://youtu.be/xj-3bDGLxIg) 


## ๐ฅ ์ด๋ณด์๋ฅผ ์ํ ์๋จ ๊ธฐ๋ก & ๊ด๋ฆฌ ์๋น์ค
  
๐งพ ์ฐ๋ น๋, ์ฑ๋ณ, ์ด๋๋, ์๋จ์ ๋ชฉํ๋ฅผ ๋ฐํ์ผ๋ก ํ `์์์ ๋น์จ ์ถ์ฒ`(ํด๋ฆฌ์ค ๋ฒ ๋ค๋ํธ ๊ณ์ฐ๋ฒ)  
๐ฅ ์ด๋ณด์๋ฅผ ์ํ `๊ธฐ๋ณธ ์๋จ` ์ ๊ณต  
๐ `๋ชจ์ ์๋จ` ์ง๋ณด๊ธฐ & ํผ๋๋ฐฑ ๋ฐ๊ธฐ ๊ธฐ๋ฅ์ ํตํ ์ฌ์ด ์๋จ ๊ตฌ์ฑ  
๐ ๋งค์ผ, ๋ผ๋๋ง๋ค ์ญ์ทจํ `์์์ฑ๋ถ ๋ถ์`  
๐ ๋ฐ์ผ๋ฆฌ ๋ถ์์ ๋ฐํ์ผ๋ก ํ `์์ ์ถ์ฒ`  
๐ฝ ์๋จ ๋ถ๋ฌ์ค๊ธฐ, ์ต๊ทผ ๋จน์ ์์ ๋ฆฌ์คํธ, ๋ฐ์ฝ๋ ๋ฑ `ํธ๋ฆฌํ ์์ ์๋ ฅ`  
๐ ํ๋ฌ ๊ฐ ์ฑ๊ณต ์ฌ๋ถ๋ฅผ ํ ๋์ ๋ณด์ฌ์ฃผ๋ `์บ๋ฆฐ๋` ๊ธฐ๋ฅ์ผ๋ก ๋๊ธฐ ๋ถ์ฌ  

## File structure
`master`
```
|-- assets    // logo, architecture img
|-- backend   
|-- docs      // presentation file
|-- exec      // porting manual, DB dump
|-- frontend  
|-- hadoop    
|-- nginx     
```

## Total Architecture
![total architecture](/assets/%ED%8A%B9%ED%99%94pjt_%ED%86%A0%ED%83%88%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98.png)

## Tech Stacks
- ๋์ปค๋ฅผ ์ด์ฉํ์ฌ ์๋ฒ๋ฅผ ๋ฐฐํฌํ์ผ๋ฏ๋ก porting manual์ ๋ฐ๋ผ ์๋ฒ์ ๋ฐฐํฌํ๋ฉด ๋๋ค.

### Environment
- OS: Windows 10
- IDE
    - VS Code
    - Eclipse
- DB: MariaDB
- Server: AWS EC2
- Docker
### BE
- Java 17
- Spring Boot 2.7.3
- Gradle
- Swagger
    - OpenAPI 3
    - springdoc-openapi v1.6.11
### FE
- React 18.2.0
- Redux 8.0.4
- react-router 5.3.3
### Hadoop
- Hadoop 3.2.2
- HBase 2.4.14
- Zookeeper 3.7.1
- javac 1.8.0_342
- 
## Data
- [์ํ์์์ฑ๋ถDB](https://various.foodsafetykorea.go.kr/nutrient/)์ ์ํ์์DB ๋ฐ์ดํฐ
    - ์ด 90608 row, 22 column
    - ํ์ํ๋ฌผ, ๋จ๋ฐฑ์ง, ์ง๋ฐฉ์ alias๋ฅผ ์ ๊ฑฐํ์ฌ ์ฌ์ฉ

## Communication Tools
- JIRA
- Mattermost
- Gitlab
- [Notion](https://pastoral-maraca-134.notion.site/fed4bc6aed4a4a50984359636f666af2)
    - ๋ ๋ง์ ์ ๋ณด๋ฅผ ์ํด ๋ธ์์ ๋ฐฉ๋ฌธํด๋ณด์ธ์
## Members
|์ด๋ฆ|์ด๋ฉ์ผ|์ญํ |
|:--:|:--|:--:|
|๊น๋์|kdw324400@gmail.com|Hadoop, HBase, Data ์ ์ |
|๊น์ง์ธ|skygazer227@gmail.com|BE, Infra|
|์ค๊ฒฝ์|didnlie@gmail.com|FE, UCC|
|์ ์ฌํ|jaeung644@gmail.com|Hadoop, HBase|
|์กฐํ๋ฆผ|rrimm012@gmail.com|FE, Design|
|ํ์ธํ|thedeny11@gmail.com|BE|
