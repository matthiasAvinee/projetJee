package ProjetJee.web.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import projetJee.web.filter.connexionFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(JUnit4.class)
public class FilterStructureTestCase {

    @Test
    public void shouldImplementFilter(){
        //GIVEN
        connexionFilter filter = new connexionFilter();
        //WHEN
        //THEN
        assertThat(filter).isInstanceOf(Filter.class);
    }

    @Test
    public void shouldHaveWebFilterAnnotation() {
        //GIVEN
        Class<connexionFilter> clazz = connexionFilter.class;
        WebFilter[] annotations = clazz.getAnnotationsByType(WebFilter.class);
        //WHEN
        //THEN
        assertThat(annotations).hasSize(1);
        assertThat(annotations[0].urlPatterns()).containsOnly("/*");
    }



    @Test
    public void shouldHaveInitMethod() throws NoSuchMethodException {
        TestUtils.shouldHaveMethod(connexionFilter.class,"init", FilterConfig.class);
    }

    @Test
    public void shouldHaveDoFilterMethod() throws NoSuchMethodException {
        TestUtils.shouldHaveMethod(connexionFilter.class,"doFilter", ServletRequest.class, ServletResponse.class,FilterChain.class);
    }

    @Test
    public void shouldHaveDestroyMethod() throws NoSuchMethodException {
        TestUtils.shouldHaveMethod(connexionFilter.class,"destroy");
    }

}
