package org.contextmapper.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.contextmapper.dsl.cml.CMLResource;
import org.contextmapper.dsl.generator.ContextMapGenerator;
import org.contextmapper.dsl.standalone.ContextMapperStandaloneSetup;
import org.contextmapper.dsl.standalone.StandaloneContextMapperAPI;

@Mojo(
    name = "generate-context-map",
    threadSafe = true,
    defaultPhase = LifecyclePhase.GENERATE_RESOURCES
)
public class ContextMapperGeneratorContextMapMojo extends AbstractContextMapperGeneratorMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        validate();
        try {
            StandaloneContextMapperAPI cmAPI = ContextMapperStandaloneSetup.getStandaloneAPI();
            CMLResource cmlResource = cmAPI.loadCML(getInput());
            cmAPI.callGenerator(cmlResource, new ContextMapGenerator(), getOutput().getAbsolutePath());
        } catch (Exception e) {
            throw new MojoFailureException(e);
        }
    }
}
