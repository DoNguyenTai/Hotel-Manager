package fit.tdc.vn.QLKS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.tdc.vn.QLKS.Entities.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	
}

