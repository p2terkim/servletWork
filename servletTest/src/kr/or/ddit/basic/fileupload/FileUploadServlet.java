package kr.or.ddit.basic.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/*
 	- Servlet 3.0이상에서 파일 업로드를 처리하려면 서블릿에 @MultipartConfig 어노테이션을 설정해야한다.
 			(Servlet3.0 이하에서는 따로 라이브러리를 추가했어야했다.)
 	- @MultipartConfig 어노테이션에서 설정할 변수
 			* fileSizeTreshold : 이 크기가 넘으면 디스크의 임시 디렉토리에 저장된다.(임시 기억장소 크기로 생각하면된다.)
 								(단위 : byte, 기본값 : 0 (무조건 임시디렉토리에 저장))
 			* maxFileSize : 한 개 파일의 최대 크기 (단위 : byte, 기본값 : -1L (무제한))
 			* maxRequestSize : 서버로 전송되는 Request데이터의 최대 크기
 								(단위 : byte, 기본값 : -1L (무제한))
 			* location : Part.write()메서드를 통해서 파일을 저장할 디렉토리 : (기본값 : "")
 			* 
 			* ** 크기 설정은 환경에 따라 적절하게 정해서...
*/


@WebServlet("/file/FileUpload.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30,
					maxRequestSize = 1024 * 1024 * 50)
public class FileUploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//사용자가 업로드한 파일이 저장될 서버쪽의 폴더 경로 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		File fileUploadDir = new File(uploadPath);
		
		//저장될 폴더가 없으면 새로 생성한다.
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		
		//------------------------------------------------------
		
		//파일이 아닌 데이터는 getParameter()메서드나 getParameterValues()메서드를 이용해서 구한다.
		request.setCharacterEncoding("utf-8");
		String memId = request.getParameter("memid");
		System.out.println("일반 파라미터데이터 값 : " + memId);
		
		String fileName = null;	//전송된 파일명이 저장될 변수 선언
		
		//Upload된 파일 정보가 저장될 List객체 생성
		List<UploadDetail> fileList = new ArrayList<UploadDetail>();
		
		/*
			- Servlet 3.0이상에 새롭게 추가된 파일 Upload용 메서드
				1. request.getParts(); ==>Part객체의 컬렋션을 반환한다.
				2. request.getParts("part이름"); ==> 지정된 이름을 가진 개별 Part객체를 반환한다.
		*/
		for(Part part : request.getParts()) {
			
			fileName = extractFileName(part);
			
			//파일이 공백("")이 아니면 이것은
			if(!"".equals(fileName)) {
				
				//1개의 업로드 파일에 대한 정보를 저장할 객체 생성
				UploadDetail detail = new UploadDetail();
				detail.setFileName(fileName);
				
				//파일 크기를 KB단위로 저장
				detail.setFileSize((long) Math.ceil(part.getSize() / 1024.0));
				try {
					
					part.write(uploadPath + File.separator + fileName);	//파일저장하기
					detail.setUploadStatus("Success");
					
				} catch (Exception e) {
					detail.setUploadStatus("Fail : " + e.getMessage());
				}
				fileList.add(detail);
			}
			
		}	//for문 끝...
		
		request.setAttribute("memId", memId);	//파라미터 값 저장
		request.setAttribute("uploadFiles", fileList);	//Upload된 파일 목록 저장
		
	  RequestDispatcher rd = request.getRequestDispatcher("/basic/fileupload/uploadFiles.jsp");
      
      rd.forward(request, response);

		
	}	//doPost메서드의 끝...
	
	//Part에서 읽어올 '파일명'을 찾아 반환하는 메서드
	/*
	    *    - Part의 구조
	    * 1) 파일이 아닐 때
	    * --------------------------123465jvjvkjhvycycy789      => Part를 구분하는 구분선
	    * Content-Disposition: form-data; name="memid"         => 파라미터명
	    *                                           => 빈줄
	    * a001                                       => 파라미터값
	    * 
	    * 2) 파일일 경우
	    * --------------------------123465jvjvkjhvycycy789                     => Part를 구분하는 구분선
	    * Content-Disposition: form-data; name="fileup1"; filename="test1.txt"      => 파일 정보
	    *                                                          => 빈줄
	    * adsfd1234@#%                                                => 파일 내용
	    * 
	    * 
	*/
	private String extractFileName(Part part) {
		String fileName = "";
		String disposition = part.getHeader("Content-Disposition");
				
		String[] items = disposition.split(";");
		
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length()-1);
			}
		}
		
		return fileName;
		
	}

}














