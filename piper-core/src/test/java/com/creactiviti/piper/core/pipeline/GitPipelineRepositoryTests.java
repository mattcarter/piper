
package com.creactiviti.piper.core.pipeline;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.creactiviti.piper.core.git.GitOperations;

public class GitPipelineRepositoryTests {
  
  @Test
  public void test1 () {
    GitPipelineRepository r = new GitPipelineRepository();
    r.setGitOperations(new DummyGitOperations());
    List<Pipeline> findAll = r.findAll();
    Assert.assertEquals("demo/hello/123",findAll.iterator().next().getId());
  }
  
  private static class DummyGitOperations implements GitOperations {
    
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    
    @Override
    public List<IdentifiableResource> getHeadFiles(String aUrl, String... aSearchPath) {
      return Arrays.asList(new IdentifiableResource("demo/hello/123", resolver.getResource("file:pipelines/demo/hello.yaml")));
    }
    @Override
    public IdentifiableResource getFile(String aUrl, String aFileId) {
      return new IdentifiableResource("demo/hello/123", resolver.getResource("file:pipelines/demo/hello.yaml"));
    }
    
  }
  
}
