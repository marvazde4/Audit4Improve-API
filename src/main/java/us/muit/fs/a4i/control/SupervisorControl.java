/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package us.muit.fs.a4i.control;
import org.kohsuke.github.*;

import java.util.List;
import java.util.logging.*;

	/**
	 * @author Isabel Rom�n Mart�nez
	 * @version 0.0
	 * Esta clase se crea para poder probar algunas de las capacidades que ofrece la api github
	 * Ser� descartada posteriormente
	 *
	 */
public class SupervisorControl {
	private static Logger log=Logger.getLogger(SupervisorControl.class.getName());
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			try {
			GitHub github = GitHubBuilder.fromEnvironment().build();
			GHMyself myinfo=github.getMyself();
			GHRepository unrepo=github.getRepository("MIT-FS/ShopManager");
			PagedIterable<GHRepository> myrepos=myinfo.listRepositories();
			PagedIterable<GHRepository> myOwnRepos=myinfo.listRepositories(10, GHMyself.RepositoryListFilter.OWNER);
			int count=1;
			for(GHRepository repo:myOwnRepos.toList()) {
				System.out.println("Nombre de mi repositorio n�mero "+count+" "+repo.getFullName());
				List<GHProject> proyectos=repo.listProjects().toList();
				int i=1;
				for(GHProject project:proyectos){
					System.out.println("Con proyecto "+i+" llamado "+project.getName()+" con id "+project.getId());
					GHProject audit= github.getProject(project.getId());
					System.out.println(audit);
					i++;
				}
				if(repo.isFork()) {
					System.out.println("Que es un fork de "+repo.fork().getName());
				}
				count++;
			}

			log.info("Mis datos "+myinfo);		
			log.info("Mi repositorio "+unrepo);
			log.info("N�mero de repositorios "+myrepos.toList().size());
			log.info("Detalles de mis repositorios "+myrepos.toList());
		}catch(Exception e) {
				log.info(e+" No se puede crear la instancia GitHub\n");
				log.info("Recuerde que debe configurar las variables de entorno GITHUB_LOGIN y GITHUB_OAUTH con su nombre de usuario y token respectivamente");
			}
		}

   
}
