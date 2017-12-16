import com.bbq.beans.Student;
import com.bbq.dao.StudentDao;
import com.bbq.dao.StudentDaoImp;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class TestStudentDao {

    private static StudentDao studentDao;

    @BeforeClass
    public static void initConn() {
        studentDao = new StudentDaoImp();
    }

    @Test
    public void testInsertStudent() {
        Student st = new Student();
        st.setName("重八");
        st.setQQ("123456");
        st.setBrotherName("大师兄");
        st.setDesire("提高效率，少饭错误");
        st.setUpdateAt(1234567);
        st.setCreateAt(123456);
        st.setCareerType(1L);
        st.setEntryDate(new Date(System.currentTimeMillis()));
        st.setSchool("张家墩");
        st.setDailyReportUrl("http://1024.ml");
        st.setIdOnSite("水上漂");
        studentDao.insertStudent(st);
    }

    @Test
    public void testgetStudentById() {
        Student student = studentDao.getStudentById(14934150);
        Assert.assertTrue(student.getId() == 14934150);
        System.out.println(student);
    }

    @Test
    public void testDelteStudentById() {
        int ret = studentDao.deleteStudentById(14934156);
        Assert.assertTrue(ret == 1);
    }

    @Test
    public void testGetByName() {
        List<Student> students = studentDao.getStudentByName("重八");
        Assert.assertNotNull(students);
        Assert.assertTrue(students.size() > 1);
        Assert.assertTrue(students.get(1).getName().equals("重八"));
    }
}
