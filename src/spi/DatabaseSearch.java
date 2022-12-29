package spi;

import java.util.List;

/**
 * 核心思想: 数据库搜索
 *
 * @author bill
 * @since 2022/12/29 14:59
 */
public class DatabaseSearch implements Search{
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("数据库搜索" + keyword);
        return null;
    }
}
