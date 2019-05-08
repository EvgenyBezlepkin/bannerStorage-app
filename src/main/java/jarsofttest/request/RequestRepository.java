package jarsofttest.request;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {

	List<Request> findByUserAgentAndIpAddrAndBannerId(String userAgent, String ipAddr, int bannerId);
}
