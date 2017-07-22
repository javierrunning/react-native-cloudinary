
Pod::Spec.new do |s|
  s.name         = "RNCloudinary"
  s.version      = "1.0.0"
  s.summary      = "RNCloudinary"
  s.description  = <<-DESC
                  RNCloudinary
                   DESC
  s.homepage     = "https://github.com"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "javier@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNCloudinary.git", :tag => "master" }
  s.source_files  = "ios/*"
  s.requires_arc = true
  s.pod_target_xcconfig = { 'SWIFT_VERSION' => '3.0' }

  s.dependency "Cloudinary", "~> 2.0"
  s.dependency "React"
  # s.dependency "React"
  #s.dependency "others"

end

  