package jspstudy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jspstudy.domain.BoardVo;
import jspstudy.domain.Criteria;
import jspstudy.domain.PageMaker;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ������ ����
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		System.out.println("command: " + command);

		String uploadPath = "E:\\open api(B)\\dev\\Jspstudy\\src\\main\\webapp\\";
		String saveFolder = "images";
		String saveFullPath = uploadPath + saveFolder;

		if (command.equals("/board/boardWrite.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/board/board.jsp");
			rd.forward(request, response);

		} else if (command.equals("/board/boardWriteAction.do")) {

			int sizeLimit = 1024 * 1024 * 15;

			MultipartRequest multi = null;

			multi = new MultipartRequest(request, saveFullPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String writer = multi.getParameter("writer");

			// �����ڿ� ����� ������ ��� ��ü�� �����Ѵ�.
			Enumeration files = multi.getFileNames();
			// ��� ���� ��ü�� ���� �̸��� ��´�.
			String file = (String) files.nextElement();
			// ����Ǵ� ���� �̸�
			String fileName = multi.getFilesystemName(file);
			// ���� ���� �̸�
			String originFileName = multi.getOriginalFileName(file);

			String ip = InetAddress.getLocalHost().getHostAddress();

			HttpSession session = request.getSession();
			int midx = (int) session.getAttribute("midx");

			BoardDao bd = new BoardDao();
			int value = bd.insertBoard(subject, content, writer, ip, midx, fileName);
			if (value == 1) {
				response.sendRedirect(request.getContextPath() + "/index.jsp");

			} else {
				response.sendRedirect(request.getContextPath() + "/board/boardWrite.do");

			}
		} else if (command.equals("/board/boardList.do")) {
			System.out.println("����Ʈ ������");

			String page = request.getParameter("page");
			if (page == null)
				page = "1";
			int pagex = Integer.parseInt(page);

			String keyword = request.getParameter("keyword");
			if (keyword == null)
				keyword = "";
			String searchType = request.getParameter("searchType");
			if (searchType == null)
				searchType = "subject";

			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setKeyword(keyword);
			scri.setSearchType(searchType);

			// ó��
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);

			// System.out.println("cnt"+cnt);

			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			request.setAttribute("pm", pm);

			ArrayList<BoardVo> alist = bd.boardSelectAll(scri);
			request.setAttribute("alist", alist); // ������(�ڿ�) ����

			// �̵�
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);

		} else if (command.equals("/board/boardContent.do")) {
			System.out.println("���뺸�� ������");

			// 1. �Ķ���Ͱ� �Ѿ��
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);

			// 2. ó����
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);

			request.setAttribute("bv", bv); // ���������� �ڿ�����

			// 3. �̵���
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);

		} else if (command.equals("/board/boardModify.do")) {
			System.out.println("�����ϱ� ������");

			// 1. �Ķ���Ͱ� �Ѿ��
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);

			// 2. ó����
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);

			request.setAttribute("bv", bv); // ���������� �ڿ�����

			// 3. �̵���
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");
			rd.forward(request, response);

		} else if (command.equals("/board/boardModifyAction.do")) {
			System.out.println("�����ϱ� ������");

			// 1. �Ķ���Ͱ� �Ѿ��
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);

			String ip = InetAddress.getLocalHost().getHostAddress();

			HttpSession session = request.getSession();
			int midx = (int) session.getAttribute("midx");

			// 2. ó����
			BoardDao bd = new BoardDao();
			int value = bd.Update(subject, content, writer, midx, bidx_);

			if (value == 1) {
				response.sendRedirect(request.getContextPath() + "/board/boardContent.do?bidx=" + bidx);

			} else {
				response.sendRedirect(request.getContextPath() + "/board/boardModify.do?bidx=" + bidx);

			}
		} else if (command.equals("/board/boardDelete.do")) {

			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);

			request.setAttribute("bidx", bidx);

			RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");
			rd.forward(request, response);

		} else if (command.equals("/board/boardDeleteAction.do")) {
			System.out.println("�����ϱ� ������");

			// 1. �Ķ���Ͱ� �Ѿ��
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);

			// 2. ó����
			BoardDao bd = new BoardDao();
			int value = bd.DeleteBoard(bidx_);

			if (value == 1) {
				response.sendRedirect(request.getContextPath() + "/board/boardList.do?bidx=" + bidx);
				
			} else {
				response.sendRedirect(request.getContextPath() + "/board/boardContent.do?bidx=" + bidx);

			}
		} else if (command.equals("/board/boardReply.do")) {

			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_b");

			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));

			request.setAttribute("bv", bv);

			RequestDispatcher rd = request.getRequestDispatcher("/board/boardReply.jsp");
			rd.forward(request, response);

		} else if (command.equals("/board/boardReplyAction.do")) {

			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_b");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String ip = InetAddress.getLocalHost().getHostAddress();

			HttpSession session = request.getSession();
			int midx = (int) session.getAttribute("midx");

			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			bv.setSubject(subject);
			bv.setContent(content);
			bv.setWriter(writer);
			bv.setIp(ip);
			bv.setMidx_(midx);

			BoardDao bd = new BoardDao(); //// ������ ȣ��
			int value = bd.replyBoard(bv);

			if (value == 1) {
				response.sendRedirect(request.getContextPath() + "/board/boardList.do");
			} else {
				response.sendRedirect(request.getContextPath() + "/board/boardModify.do?bidx=" + bidx);
			}

		} else if (command.equals("/board/fileDownload.do")) {
			// ���� �̸��� �Ѱܹ޴´�.
			String filename = request.getParameter("filename");
			// ������ ��ü���
			String filePath = saveFullPath + File.separator + filename;
			Path source = Paths.get(filePath);
			String mimeType = Files.probeContentType(source);
			// ��� ������ ������ ���� ������ ��´�.
			response.setContentType(mimeType);

			String sEncoding = new String(filename.getBytes("utf-8"));
			// ��������� ���� �̸��� ��´�.
			response.setHeader("Content-Disposition", "attachment; fileName=" + sEncoding);

			// �ش� ��ġ�� �ִ� ������ �о���δ�.
			FileInputStream fileInputStream = new FileInputStream(filePath);
			// ���� ����
			ServletOutputStream servletOutStream = response.getOutputStream();

			byte[] b = new byte[4096];
			int read = 0;
			while ((read = fileInputStream.read(b, 0, b.length)) != -1) {
				servletOutStream.write(b, 0, read);
			}

			servletOutStream.flush();
			servletOutStream.close();
			fileInputStream.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
