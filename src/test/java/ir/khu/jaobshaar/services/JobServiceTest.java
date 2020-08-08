package ir.khu.jaobshaar.services;

import ir.khu.jaobshaar.component.job.JobManager;
import ir.khu.jaobshaar.entity.model.Job;
import ir.khu.jaobshaar.service.criteria.JobCriteria;
import ir.khu.jaobshaar.service.domain.JobDomain;
import ir.khu.jaobshaar.service.domain.ResumeDomain;
import ir.khu.jaobshaar.service.dto.JobDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Random;

public class JobServiceTest {

	private final JobManager jobManager;

	public JobServiceTest() {
		this.jobManager = Mockito.mock(JobManager.class);
	}

	@Test
	public void addJob() {
		Random random = new Random();
		JobDTO jobDTO = new JobDTO(random.nextInt(18), random.nextInt(3),
				random.nextInt(2), "", "");
		JobDomain job = jobManager.addJob(jobDTO);
		Assert.assertNull(job);
	}

	@Test
	public void getEmployeeJobs() {
		Pageable pa = new Pageable() {
			@Override
			public int getPageNumber() {
				return new Random().nextInt();
			}

			@Override
			public int getPageSize() {
				return new Random().nextInt(9);
			}

			@Override
			public long getOffset() {
				return new Random().nextInt(20);
			}

			@Override
			public Sort getSort() {
				return null;
			}

			@Override
			public Pageable next() {
				return null;
			}

			@Override
			public Pageable previousOrFirst() {
				return null;
			}

			@Override
			public Pageable first() {
				return null;
			}

			@Override
			public boolean hasPrevious() {
				return false;
			}
		};
		List<JobDomain> result = jobManager.getEmployeeJobs(new JobCriteria(), pa);
		Assert.assertNotNull(result);
	}

	@Test
	public void getEmployerJobs() {
		Pageable pa = new Pageable() {
			@Override
			public int getPageNumber() {
				return new Random().nextInt();
			}

			@Override
			public int getPageSize() {
				return new Random().nextInt(9);
			}

			@Override
			public long getOffset() {
				return new Random().nextInt(20);
			}

			@Override
			public Sort getSort() {
				return null;
			}

			@Override
			public Pageable next() {
				return null;
			}

			@Override
			public Pageable previousOrFirst() {
				return null;
			}

			@Override
			public Pageable first() {
				return null;
			}

			@Override
			public boolean hasPrevious() {
				return false;
			}
		};
		List<JobDomain> result = jobManager.getEmployerJobs(pa);
		Assert.assertNotNull(result);
	}

	@Test
	public void getJobById() {
		Long jobId = new Random().nextLong();
		JobDomain job = jobManager.getJobById(jobId);
		Assert.assertNull(job);
	}

	@Test
	public void getJobResume() {
		Long jobId = new Random().nextLong();
		List<ResumeDomain> resume = jobManager.getJobResume(jobId);
		Assert.assertNotNull(resume);
	}

	@Test
	public void findSameJobs() {
		Long jobId = new Random().nextLong();
		List<JobDomain> jobDomainList = jobManager.findSameJobs(jobId);
		Assert.assertNotNull(jobDomainList);
	}

	@Test
	public void updateJob() {
		Random random = new Random();
		JobDTO jobDTO = new JobDTO(random.nextLong() + 1, random.nextInt(18),
				random.nextInt(3), random.nextInt(2), "", "");
		JobDomain jobDomain = jobManager.updateJob(jobDTO);
		Assert.assertNull(jobDomain);
	}

	@Test
	public void deleteById() {
		Long id = new Random().nextLong();
		jobManager.deleteById(id);
		Job jobDomain = jobManager.findJobById(id);
		Assert.assertNull(jobDomain);
	}
}
