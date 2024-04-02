package todo.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.dto.MemberDTO;
import todo.service.MemberService;

@WebFilter("/todo/*")
public class LoginCheckFilter extends HttpFilter implements Filter {
       
    public LoginCheckFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		
		
		if(session.getAttribute("loginInfo") != null) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		
		Cookie cookie = findCookie(httpRequest.getCookies(), "remember-me");
		
		if(cookie == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			return;
		}
		
		// 여기까지 왔으면 쿠키가 존재한 상황
		String uuid = cookie.getValue();
		
		try {
			MemberDTO dto = MemberService.INSTANCE.selectByUuid(uuid);
			
			if(dto == null) {
				throw new Exception("Cookie value is not valid");
			}
			
			//회원정보를 세션에 추가
			session.setAttribute("loginInfo", dto);
			chain.doFilter(httpRequest, httpResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	
	private Cookie findCookie(Cookie[] cookies, String cookieName) {
		if(cookies == null || cookies.length == 0) {
			return null;
		}
		
		Optional<Cookie> result = Arrays.stream(cookies)
				.filter(ck -> ck.getName().equals(cookieName))
				.findFirst();
		
		return result.isPresent()?result.get():null;
	}

}
