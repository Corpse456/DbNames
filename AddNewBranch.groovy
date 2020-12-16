Map<String, String> env = System.getenv()
createBranch(env['_DB_ACCOUNT'], env['TEMP'])

static void createBranch(String accountName, String directory) {
    print execute("git clone https://bitbucket.org/versonix/sw-jenkins-deploy", directory).text
    directory += "/sw-jenkins-deploy"
    String text = execute("git ls-remote --quiet --heads origin " + accountName, directory).text
    if (text.isEmpty()) {
        execute("git checkout -b " + accountName, directory)
        execute("git push origin " + accountName, directory)
    }
}

private static Process execute(String command, String directory) {
    return command.execute(Collections.emptyList(), new File(directory))
}
