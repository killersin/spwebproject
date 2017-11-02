package org.zerock.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import org.zerock.domain.UserVO;
import org.zerock.service.UserService;

//특정 경로에 접근하는 경우 현재 사용자가 로그인한 상태의
//사용자인지 체크하는 역할의 클래스
public class AuthInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Inject
	UserService service;
	
	//로그인 성공 후 원래 URI로 이동시키는 작업
	private void saveDest(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null || query.equals("null")) {
			query="";
		}else {
			query = "?" + query;
		}
		
		if(req.getMethod().equals("GET")) {
			logger.info("dest : "+ (uri+query));
			req.getSession().setAttribute("dest", uri+query);
		}
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
					throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("login") ==null) {
			logger.info("current user is not logined");
			response.sendRedirect("/user/login");
			saveDest(request);
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); //세션에 값이 없는 경우 loginCookie를 갖는 지 체크
			if(loginCookie != null) {
				UserVO userVO = service.checkLoginBefore(loginCookie.getValue()); //사용자의 정보가 존재한다면 
				logger.info("USERVO: " + userVO);
				if(userVO != null) {
					session.setAttribute("login", userVO); //세션에 다시 사용자 정보를 넣어준다.
					return true;
				}
			}
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}
	
	
	
}
