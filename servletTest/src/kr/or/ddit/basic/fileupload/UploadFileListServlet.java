package kr.or.ddit.basic.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadFileListServlet
 */
@WebServlet("/file/UploadFileList.do")
public class UploadFileListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //1 사용자가 업로드한 파일이 저장될 서버쪽의 폴더 경로 설정
      String uploadPath = "d:/d_other/uploadFiles";
      
      //2 저장될 폴더가 없으면 새로 생성한다.
      File fileUploadDir = new File(uploadPath);
      
      if(!fileUploadDir.exists()) {
         fileUploadDir.mkdirs();   //중간에 존재하지 않는 폴더들 까지 모두 생성
      }
      
      //3 파일이 저장된 폴더에 있는 파일 전체 목록을 구해와서 List에 담아준다
      File[] allFiles = fileUploadDir.listFiles();
      List<UploadDetail> fileList = new ArrayList<UploadDetail>();
      
      for(File file : allFiles) {
         if(file.isFile()) {
            //3-1파일 정보가 저장될 객체 생성
            UploadDetail detail = new UploadDetail();
            detail.setFileName(file.getName());
            detail.setFileSize((long)Math.ceil(file.length()/1024.0));
            detail.setUploadStatus("Uploaded");
            fileList.add(detail);
         }
      }
      
      request.setAttribute("uploadFiles", fileList);
      
      RequestDispatcher rd = request.getRequestDispatcher("/basic/fileupload/allFiles.jsp");
      rd.forward(request, response);
   }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}