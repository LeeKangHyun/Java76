package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 파일 업로드 */
@WebServlet("/file/upload")
public class Servlet11 extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(
      HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    // 멀티파트 형식으로 받은 데이터를 getParameter()를 통해 값을 꺼낼 수 없다.
    String name = request.getParameter("name");
    String photo = request.getParameter("photo");
    
    // 멀티파트 형식으로 받은 데이터를 직접 분석하여 값 꺼내기
    // => 하지 말라! 힘들다! 그냥 오픈소스 라이브러리 써라!
    InputStream in = request.getInputStream();
    for (int i = 0; i < 100; i++) {
      System.out.printf("%c", in.read());
    }
    
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.printf("이름 = %s\n", name);
    out.printf("사진 = %s\n", photo);
  }
}

/* multipart/form-data 형식의 요청 프로토콜 예:
POST /web01t/file/upload HTTP/1.1
Host: localhost:8080
Content-Length: 6176
Cache-Control: max-age=0
Accept: text/html,application/xhtml+xm...
Origin: http://localhost:8080
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Int...
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7wGOI61l26HqPkkd
Referer: http://localhost:8080/web01t/file/UploadForm.jsp
Accept-Encoding: gzip, deflate
Accept-Language: ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4
Cookie: JSESSIONID=F1099DC4A8C36AA81776205885E47FF3

------WebKitFormBoundary7wGOI61l26HqPkkd
Content-Disposition: form-data; name="name"

홍길동
------WebKitFormBoundary7wGOI61l26HqPkkd
Content-Disposition: form-data; name="photo"; filename="photo02.jpeg"
Content-Type: image/jpeg

���� JFIF      �� � 
...
------WebKitFormBoundary7wGOI61l26HqPkkd--

 */






