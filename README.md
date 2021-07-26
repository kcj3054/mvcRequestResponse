# mvcRequestResponse
# spring mvc 

## request
requestMapping
@RequestParam, @ModelAttribut.. -> 

RequestParam은 요청 int string일시 생략 가능 다른 타입은 ModelAttribute가 받는다 

 InputStream (Reader) : Http 요청 메시지 바디의 내용을 직접 조회
 OutputStream (Writer) : Http 응답 메시지 바디에 직접 결과 출력
  
String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
-> StreamUtils를 이용해서 inputStream을 String으로 변환        
 
## response
responseBody
HttpEntity


### HttpMessageConverter 내용 

HttpMessageConverter가 ResponseBody의 역할을 도와준다, 바로 http바디에 데이터를 넣을 경우 string, json골라준다 

