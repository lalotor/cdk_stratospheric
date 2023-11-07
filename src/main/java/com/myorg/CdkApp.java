package com.myorg;

import dev.stratospheric.cdk.SpringBootApplicationStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
//import software.amazon.awscdk.Environment;
//import software.amazon.awscdk.StackProps;

//import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public class CdkApp {
    public static void main(final String[] args) {
        App app = new App();

//        new CdkStack(app, "CdkStack", StackProps.builder()
                // If you don't specify 'env', this stack will be environment-agnostic.
                // Account/Region-dependent features and context lookups will not work,
                // but a single synthesized template can be deployed anywhere.

                // Uncomment the next block to specialize this stack for the AWS Account
                // and Region that are implied by the current CLI configuration.
                /*
                .env(Environment.builder()
                        .account(System.getenv("CDK_DEFAULT_ACCOUNT"))
                        .region(System.getenv("CDK_DEFAULT_REGION"))
                        .build())
                */

                // Uncomment the next block if you know exactly what Account and Region you
                // want to deploy the stack to.
                /*
                .env(Environment.builder()
                        .account("123456789012")
                        .region("us-east-1")
                        .build())
                */

        // For more information, see https://docs.aws.amazon.com/cdk/latest/guide/environments.html
//        .build());

        String accountId = (String) app.getNode().tryGetContext("accountId");
        requireNonNull(accountId, "context variable 'accountId' must not be null");

        String region = (String) app.getNode().tryGetContext("region");
        requireNonNull(region, "context variable 'region' must not be null");

        new SpringBootApplicationStack(app, "SpringBootApplication", makeEnv(accountId, region),
                "docker.io/stratospheric/todo-app-v1:latest");

        app.synth();
    }

    private static Environment makeEnv(String accountId, String region) {
        return Environment.builder()
                .account(accountId)
                .region(region)
                .build();
    }
}
