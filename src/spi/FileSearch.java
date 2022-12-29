package spi;

import java.util.List;

/**
 * 核心思想: 文件搜索
 *
 * @author bill
 * @since 2022/12/29 14:58
 */
public class FileSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索" + keyword);
        return null;
    }
}
