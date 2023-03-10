import org.dows.account.AccountApplication;
import org.dows.account.api.AccountIdentifierApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { AccountApplication.class })
public class TestAccountIdentifer {

    @Autowired
    private AccountIdentifierApi accountIdentifierApi;
     
}
