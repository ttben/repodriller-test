
package fr.cnrs.i3s.sparks.composition.merge.study;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.*;

import java.io.File;

public class MergeDetector implements Study {


    public void execute() {
        CSVFile writer = new CSVFile("./result.csv");
        writer.write("commitWithoutBranch");

        File f = new File("ExoPlayer/");

        SCMRepository singleGitRemoteRepositoryBuilder = GitRepository.singleProject(f.getAbsolutePath());
        SCMRepository scmRepository = singleGitRemoteRepositoryBuilder;
        new RepositoryMining()
                .in(scmRepository)
                .through(Commits.all())
                .process(new MergeCommitVisitor(), writer)
                .mine();
    }

    public static void main(String[] args) {
        RepoDriller repoDriller = new RepoDriller();
        repoDriller.start(new MergeDetector());


    }
}