package jspstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspstudy.domain.MemberVo;
import jspstudy.service.MemberDao;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// �ּ��� Ǯ��θ� ����
		String uri = request.getRequestURI();
		// ������Ʈ �̸��� ����
		String pj = request.getContextPath();
		// ������Ʈ�� �̸��� �� ������ �����θ� ����
		String command = uri.substring(pj.length());
		System.out.println("command:" + command);

		if (command.equals("/member/memberJoinAction.do")) {

			// input ��ü�� �̸��� ���� �Ķ���͸� ȣ���ϸ� �� ��ü�� ���� �����Ѵ�
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			String memberName = request.getParameter("memberName");
			String memberEmail = request.getParameter("memberEmail");
			String memberGender = request.getParameter("memberGender");
			String memberAddr = request.getParameter("memberAddr");
			String memberPhone = request.getParameter("memberPhone");
			String memberJumin = request.getParameter("memberJumin");
			String[] memberHobby = request.getParameterValues("memberHobby");
			// �������� ���� ���� ��ü�� �̸��� ȣ���ϸ� �迭������ ���� �����Ѵ�.
			// �������� ���� ���� ���� getParameterValues�� ����Ѵ�.

			String hobby = "";
			for (int i = 0; i < memberHobby.length; i++) {
				hobby = hobby + "," + memberHobby[i];
				// out.println(memberHobby[i] + "<br>");
			}
			hobby = hobby.substring(1);
			String ip = InetAddress.getLocalHost().getHostAddress();
			// IP �ּҰ��� �̾Ƴ��ִ� �޼ҵ� getHostAddress

			MemberDao md = new MemberDao();
			int value = md.insertMember(memberId, memberPwd, memberName, memberEmail, memberGender, memberAddr,
					memberPhone, memberJumin, hobby, ip);

			// PrintWriter out = response.getWriter();

			if (value == 1) {
				response.sendRedirect(request.getContextPath() + "/member/memberList.do");
				// out.println("<script>alert('ȸ������
				// ����');location.href='"+request.getContextPath()+"/index.jsp'</script>");
			} else {
				response.sendRedirect(request.getContextPath() + "/member/memberJoin.do");
				// out.println("<script>alert('ȸ������
				// ����');location.href='./memberJoin.jsp'</script>");
			}

		} else if (command.equals("/member/memberJoin.do")) {
			System.out.println("test");
			// ȸ������ �������� ������ ó���� ��
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberJoin.jsp"); // Ŭ������ �� Ȯ���� ��
			rd.forward(request, response);
			// forward�� ���������� �̵��ϴ� ���

		} else if (command.equals("/member/memberList.do")) {

			MemberDao md = new MemberDao();
			ArrayList<MemberVo> alist = md.memberSelectAll();

			request.setAttribute("alist", alist);

			RequestDispatcher rd = request.getRequestDispatcher("/member/memberList.jsp");
			rd.forward(request, response);

		} else if (command.equals("/member/memberLogin.do")) {

			System.out.println("�׽�Ʈ");

			RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
			rd.forward(request, response);

		} else if (command.equals("/member/memberLoginAction.do")) {

			// 1. �Ѿ�� ���� �޴´�.
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");

			// 2.ó��(�޼ҵ� ���)
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLogin(memberId, memberPwd);

			HttpSession session = request.getSession();

			// 3. �̵�
			if (mv != null) {
				session.setAttribute("midx", mv.getMidx());
				session.setAttribute("memberId", mv.getMemberid());
				session.setAttribute("memberName", mv.getMembername());

				if (session.getAttribute("saveUrl") != null) {
					response.sendRedirect((String) session.getAttribute("saveUrl"));
				} else {
					response.sendRedirect(request.getContextPath() + "/member/memberList.do");
				}

			} else {
				response.sendRedirect(request.getContextPath() + "/member/memberLogin.do");
			}

		} else if (command.equals("/member/memberLogout.do")) {

			HttpSession session = request.getSession();
			session.invalidate();

			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
