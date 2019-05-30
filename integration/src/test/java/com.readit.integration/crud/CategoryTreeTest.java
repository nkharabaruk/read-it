package com.readit.integration.crud;

import static junit.framework.TestCase.assertEquals;
import com.readit.integration.client.CategoryClient;
import com.readit.integration.client.CategoryTreeClient;
import com.readit.rest.dto.CategoryDTO;
import com.readit.rest.dto.CategoryTree;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = "spring.main.allow-bean-definition-overriding=true")
public class CategoryTreeTest {

    @Autowired
    CategoryClient categoryClient;

    @Autowired
    CategoryTreeClient categoryTreeClient;

    private CategoryDTO parent;
    private CategoryDTO child_1;
    private CategoryDTO child_2;
    private CategoryDTO child_1_1;

    @Before
    public void setUp() {
        categoryClient.delete();

        parent = new CategoryDTO();
        parent.setName("parent");
        parent = categoryClient.save(parent);

        child_1 = new CategoryDTO();
        child_1.setName("child_1");
        child_1.setParentId(parent.getId());
        child_1 = categoryClient.save(child_1);

        child_2 = new CategoryDTO();
        child_2.setName("child_2");
        child_2.setParentId(parent.getId());
        child_2 = categoryClient.save(child_2);

        child_1_1 = new CategoryDTO();
        child_1_1.setName("child_1_1");
        child_1_1.setParentId(child_1.getId());
        child_1_1 = categoryClient.save(child_1_1);
    }

    @Test
    public void testRootElement() {
        CategoryTree categoryTree = categoryTreeClient.get();

        assertEquals(null, categoryTree.getId());
        assertEquals(null, categoryTree.getName());
    }

    @Test
    public void testParentElementFromRoot() {
        CategoryTree categoryTree = categoryTreeClient.get();

        assertTreeIsEqualToDTO(categoryTree.getChildren().get(0), parent);
    }

    @Test
    public void testParentElementFromItself() {
        CategoryTree parentTree = categoryTreeClient.get(parent.getId());

        assertTreeIsEqualToDTO(parentTree, parent);
    }

    @Test
    public void testParentChildren() {
        CategoryTree parentTree = categoryTreeClient.get(parent.getId());

        assertTreeIsEqualToDTO(parentTree.getChildren().get(0), child_1);
        assertTreeIsEqualToDTO(parentTree.getChildren().get(1), child_2);
    }

    @Test
    public void testChildChild() {
        CategoryTree childTree = categoryTreeClient.get(child_1.getId());

        assertTreeIsEqualToDTO(childTree.getChildren().get(0), child_1_1);
    }

    private void assertTreeIsEqualToDTO(CategoryTree tree, CategoryDTO dto) {
        assertEquals(dto.getId(), tree.getId());
        assertEquals(dto.getName(), tree.getName());
    }

}
