import model.MyBatisUtil;
import model.CustomerMapper;
import org.apache.ibatis.session.SqlSession;
import view.CustomerView;
import controller.CustomerController;

public class Main {
    public static void main(String[] args){
        SqlSession session = MyBatisUtil.getSqlSession();
        CustomerMapper mapper = session.getMapper(CustomerMapper.class);
        
        CustomerView view = new CustomerView();
        CustomerController controller = new CustomerController(view, mapper, session);
        
        view.setVisible(true);
    }
}
