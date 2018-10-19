package ProjetJee.core.entity;

import javassist.Modifier;
import org.assertj.core.api.ListAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.List;

@RunWith(JUnit4.class)
public class CatStructureTest {

    @Test
    public void shouldHaveIdField() throws NoSuchFieldException, SecurityException {
        Field field = Cat.class.getDeclaredField("id");
        assertThat(field).isNotNull();
        assertThat(field.getType()).isEqualTo(long.class);
        assertThat(Modifier.isPrivate(field.getModifiers())).isTrue();
    }

    @Test
    public void shouldHaveNameField() throws NoSuchFieldException, SecurityException {
        Field field = Cat.class.getDeclaredField("name");
        assertThat(field).isNotNull();
        assertThat(field.getType()).isEqualTo(String.class);
        assertThat(Modifier.isPrivate(field.getModifiers())).isTrue();
    }

    @Test
    public void shouldHaveUserField() throws NoSuchFieldException, SecurityException {
        Field field = Cat.class.getDeclaredField("user");
        assertThat(field).isNotNull();
        assertThat(field.getType()).isEqualTo(User.class);
        assertThat(Modifier.isPrivate(field.getModifiers())).isTrue();
    }

    @Test
    public void shouldHavePostsField() throws NoSuchFieldException, SecurityException {
        Field field = Cat.class.getDeclaredField("posts");
        assertThat(field).isNotNull();
        assertThat(field.getType()).isEqualTo(List.class);
        assertThat(Modifier.isPrivate(field.getModifiers())).isTrue();
    }

}
