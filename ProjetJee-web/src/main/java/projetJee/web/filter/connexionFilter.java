package projetJee.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/user/*")
public class connexionFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String identifiant = (String) httpRequest.getSession().getAttribute("userConnecte");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (identifiant == null || "".equals(identifiant)) {

            httpResponse.sendRedirect("../connexion");
        } else

            filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
