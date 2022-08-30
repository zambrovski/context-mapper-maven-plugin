package org.contextmapper.maven;

import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenRepository;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;

import java.io.File;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

/**
 * Parametrized Generator Mojo I-Test.
 */
@MavenJupiterExtension
@MavenRepository
public class GeneratePlantUmlMojoIT {

    @MavenTest
    public void fail_missing_input(MavenExecutionResult result) {
        assertThat(result).isFailure();
        // TODO: add more assertions about correct error message
    }

    @MavenTest
    public void generate_plant_uml(MavenExecutionResult result) {
        assertThat(result).isSuccessful();
        assertThat(result).project().hasTarget().withFile("generated-sources/cml/Insurance-Example-Model_ContextMap.svg");
        assertThat(result).project().hasTarget().withFile("generated-sources/cml/Insurance-Example-Model_ContextMap.png");
        assertThat(result).project().hasTarget().withFile("generated-sources/cml/Insurance-Example-Model_ContextMap.gv");
    }

    @MavenTest
    public void generate_plant_uml_to_default_output(MavenExecutionResult result) {
        assertThat(result).isSuccessful();
        assertThat(result).project().hasTarget().withFile("context-mapper/Insurance-Example-Model_ContextMap.svg");
        assertThat(result).project().hasTarget().withFile("context-mapper/Insurance-Example-Model_ContextMap.png");
        assertThat(result).project().hasTarget().withFile("context-mapper/Insurance-Example-Model_ContextMap.gv");
    }
}