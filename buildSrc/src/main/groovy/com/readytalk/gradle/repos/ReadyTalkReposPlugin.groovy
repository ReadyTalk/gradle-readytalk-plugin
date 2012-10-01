package com.readytalk.gradle.repos

import org.gradle.api.Project
import org.gradle.api.Plugin

class ReadyTalkReposPlugin implements ReposConvention {

  Project project
  static String ivyMainResolverName = 'readytalk_ivy_main'
  static String mavenMainResolverName = 'readytalk_maven_main'
  static String snapshotPublishRepoName = 'readytalk_snapshot_publish'
  static String ivyLayout = '[organization]/[module]/[revision]/ivy-[revision](-[classifier]).xml'
  static String ivyArtifactLayout = '[organization]/[module]/[revision]/[module]-[revision](-[classifier]).[ext]'
  String username, password
  Closure credentials
  String repoType

  void apply(Project project) {
    this.project = project

    checkRequiredProjectVariables()
    setCredentials(project.'artifactory_username', project.'artifactory_password')
    setRepoType(project.'type')

    project.apply {
      plugin 'artifactory'
    }

    addLocal()
    addMainRepo()
    addSnapshotPublishRepo()
  }

  void addLocal() {
    def local = new LocalRepo()
    local.apply(project)
  }

  void setCredentials(String user, String pass) {
    username = user
    password = pass

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

  void checkRequiredProjectVariables() {
    assert project.has('type')
    assert project.has('artifactory_root')
    assert project.has('artifactory_main')
    assert project.has('artifactory_username')
    assert project.has('artifactory_password')
  }

  void addMainRepo() {

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
          
          username = username
          password = password

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
          username = username
          password = password

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