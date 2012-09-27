class PluginFactory {
  
  def static factory
  def static getRepos() { return factory.repos.newInstance() }
  def static getPublishers() { return factory.publishers.newInstance() }
}