package mail.netease;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;


public class ImapTest {
    public static void main(String[] args) throws Exception {
        receiveWithImap();
    }

    public static void receiveWithImap() throws Exception {
        Properties netProp = new Properties();
        String path = System.getProperty("user.dir") + File.separator + "src/mail/config/netease.properties";
        FileInputStream fis = new FileInputStream(path);
        netProp.load(fis);
        fis.close();

        String user = netProp.getProperty("user");// 邮箱的用户名
        String password = netProp.getProperty("pass"); // 邮箱的密码

        Properties prop = System.getProperties();
        prop.put("mail.store.protocol", "imap");
        prop.put("mail.imap.host", "imap.163.com");
        prop.put("mail.smtp.auth", "true");

        //这部分就是解决异常的关键所在，设置IAMP ID信息
        HashMap<String, String> IAM = new HashMap<>(4);
        //带上IMAP ID信息，由key和value组成，例如name，version，vendor，support-email等。
        // 这个value的值随便写就行
        IAM.put("name", "myname");
        IAM.put("version", "1.0.0");
        IAM.put("vendor", "myclient");
        IAM.put("support-email", "testmail@test.com");

        Session session = Session.getInstance(prop);

        // 使用imap会话机制，连接服务器
        int total = 0;
        IMAPStore store = (IMAPStore) session.getStore("imap");
        store.connect(user, password);
        store.id(IAM);

        IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱
        folder.open(Folder.READ_WRITE);
        // 获取总邮件数
        total = folder.getMessageCount();
        System.out.println("-----------------共有邮件：" + total + " 封--------------");

        // 得到收件箱文件夹信息，获取邮件列表
        System.out.println("未读邮件数：" + folder.getUnreadMessageCount());
        Message[] messages = folder.getMessages();
        int messageNumber = 0;
        for (Message message : messages) {
            System.out.println("发送时间：" + message.getSentDate());
            System.out.println("主题：" + message.getSubject());
            System.out.println("内容：" + message.getContent());
            Flags flags = message.getFlags();
            if (flags.contains(Flags.Flag.SEEN)) {
                System.out.println("这是一封已读邮件");
            } else {
                System.out.println("未读邮件");
            }

            //每封邮件都有一个MessageNumber，可以通过邮件的MessageNumber在收件箱里面取得该邮件
            messageNumber = message.getMessageNumber();
        }

        if (messageNumber != 0) {
            Message message = folder.getMessage(messageNumber);
            System.out.println(message.getContent() + message.getContentType());

            // 删除邮件
            message.setFlag(Flags.Flag.DELETED, true);
        }

        // 释放资源
        folder.close(true);
        store.close();
    }
}
