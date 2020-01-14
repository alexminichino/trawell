package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Complaint;
import com.trawell.repositories.ComplaintRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

  /**
 * @author Paolo Fasano
 */
@Service
public class ComplaintService implements IComplaintService {

    @Autowired
    private ComplaintRepository ComplaintRepository;

    @Override
    public Collection<Complaint> findAll() {
        ArrayList<Complaint> complaints = new ArrayList<>();
        ComplaintRepository.findAll().forEach(complaints::add);
        return complaints;
    }

    @Override
    public Complaint findOne(Long i) {
        Optional<Complaint> complaint = ComplaintRepository.findById(i);
        return complaint.isPresent() ? complaint.get() : null;
    }

    @Override
    public Complaint create(Complaint complaint) {
        if (complaint.getId() != null) {
            //cannot create User with specified Id value
            return null;
        }        
        Complaint savedComplaint = ComplaintRepository.save(complaint);
        return savedComplaint;
    }

    @Override
    public Complaint update(Complaint complaint) {
        Complaint complaintPersisted = findOne(complaint.getId());
        if (complaintPersisted == null) {
            //cannot find Complaint with specified Id value
            return null;
        }
        Complaint updatedComplaint = ComplaintRepository.save(complaint);
        return updatedComplaint;
    }

    @Override
    public void delete(Long id) {
         ComplaintRepository.delete(findOne(id));
    }
    
  
}