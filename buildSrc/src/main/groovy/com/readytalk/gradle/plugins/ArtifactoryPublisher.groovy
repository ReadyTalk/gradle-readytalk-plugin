package com.readytalk.gradle.plugins

import com.readytalk.gradle.tasks.Publish
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.Task
import org.gradle.api.InvalidUserDataException

import org.gradle.api.DefaultTask

class ArtifactoryPublisher implements Plugin<Project> {

  private Project project
  private static String ivyMainResolverName = 'readytalk_ivy_main'
  private static String mavenMainResolverName = 'readytalk_maven_main'
  private static String snapshotPublishRepoName = 'readytalk_ivy_snapshots'
  private static String ivyLayout = '[organization]/[module]/[revision]/ivy-[revision](-[classifier]).xml'
  private static String ivyArtifactLayout = '[organization]/[module]/[revision]/[module]-[revision](-[classifier]).[ext]'
  private String artifactoryRoot, artifactoryMain
  private Closure credentials
  private String repoType

  void apply(Project target) {
    this.project = target

    if(setupProperties()) {

      addResolverRepo()

      project.apply {
        plugin 'artifactory'
      }

      addSnapshotPublishRepo()
      addTask()

    } else {
      throw new InvalidUserDataException('HALT! Set up your credentials!')
    }
  }

  void addTask() {
    // The artifactoryPublish looks for itself in the task graph,
    // so we have to define it like this
    project.tasks.add(name: 'publish', type: DefaultTask) {
      description 'Install project into corporate Artifactory'
      group 'publish'
      dependsOn 'artifactoryPublish'
    }
    
  }

  void setCredentials(String user, String pass) {
    credentials = {
      setUsername user
      setPassword pass
    }
  }

  void setRepoType(String type) {
    if(type == 'plugin') {
      repoType = 'plugins'
    } else if (type == 'external') {
      repoType = 'ext'
    } else {
      repos = 'libs'
    }
  }

  void setArtifactoryRoot(String root) {
    artifactoryRoot = root
  }

  void setArtifactoryMain(String main) {
    artifactoryMain = main
  }

  boolean setupProperties() {
    if(project.has('type') &&
       project.has('artifactory_root') &&
       project.has('artifactory_main') &&
       project.has('artifactory_username') &&
       project.has('artifactory_password')) {

      setCredentials(project.'artifactory_username',
                     project.'artifactory_password')
      setRepoType(project.'type')
      setArtifactoryRoot(project.'artifactory_root')
      setArtifactoryMain(project.'artifactory_main')

      return true
    }
    return false
  }

  void addResolverRepo() {

    def ecovate_repo = {
      url project.'artifactory_main'
      credentials credentials
    }

    project.repositories {
      ivy(ecovate_repo).name = ivyMainResolverName
      maven(ecovate_repo).name = mavenMainResolverName
    }
  }

  void addSnapshotPublishRepo() {

    project.artifactory {
      contextUrl = project.'artifactory_root'

      publish {
        repository {
          repoKey = "${repoType}-snapshots-local"
          
          username = credentials.username
          password = credentials.password

          ivy {
            ivyLayout = ivyLayout
            artifactLayout = ivyArtifactLayout
            mavenCompatible = true
          }
        }

        defaults {
          publishIvy = true
          publishPom = false
        }
      }

      resolve {
        repository {
          repoKey = 'repo'
          username = credentials.username
          password = credentials.password

          ivy {
            ivyLayout = ivyLayout
            artifactLayout = ivyArtifactLayout
            mavenCompatible = true
          }
        }
      }

    }

  }

}